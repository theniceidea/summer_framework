package com.fmk.framework.basic;
import cn.hutool.core.util.RandomUtil;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

public class CityHash {

    private static final long k0 = 0xc3a5c85c97cb3127L;
    private static final long k1 = 0xb492b66fbe98f273L;
    private static final long k2 = 0x9ae16a3b2f90404fL;
    private static final long k3 = 0xc949d7c7509e6557L;

    private static long toLongLE(byte[] b, int i) {
        return (((long) b[i + 7] << 56) +
                ((long) (b[i + 6] & 255) << 48) +
                ((long) (b[i + 5] & 255) << 40) +
                ((long) (b[i + 4] & 255) << 32) +
                ((long) (b[i + 3] & 255) << 24) +
                ((b[i + 2] & 255) << 16) +
                ((b[i + 1] & 255) << 8) +
                ((b[i + 0] & 255) << 0));
    }

    private static long toIntLE(byte[] b, int i) {
        return (((b[i + 3] & 255L) << 24) + ((b[i + 2] & 255L) << 16) + ((b[i + 1] & 255L) << 8) + ((b[i + 0] & 255L) << 0));
    }

    private static long fetch64(byte[] s, int pos) {
        return toLongLE(s, pos);
    }

    private static long fetch32(byte[] s, int pos) {
        return toIntLE(s, pos);
    }

    private static int staticCastToInt(byte b) {
        return b & 0xFF;
    }

    private static long rotate(long val, int shift) {
        return shift == 0 ? val : (val >>> shift) | (val << (64 - shift));
    }

    private static long rotateByAtLeast1(long val, int shift) {
        return (val >>> shift) | (val << (64 - shift));
    }

    private static long shiftMix(long val) {
        return val ^ (val >>> 47);
    }

    private static final long kMul = 0x9ddfea08eb382d69L;

    private static long hash128to64(long u, long v) {
        long a = (u ^ v) * kMul;
        a ^= (a >>> 47);
        long b = (v ^ a) * kMul;
        b ^= (b >>> 47);
        b *= kMul;
        return b;
    }

    private static long hashLen16(long u, long v) {
        return hash128to64(u, v);
    }

    private static long hashLen16(long u, long v, long kmul) {
        long a = (u ^ v) * kmul;
        a ^= (a >>> 47);
        long b = (v ^ a) * kmul;
        b ^= (b >>> 47);
        b *= kmul;
        return b;
    }

    private static long hashLen0to16(byte[] s, int pos, int len) {
        if (len >= 8) {
            long kmul = k2 + len * 2;
            long a = fetch64(s, pos + 0) + k2;
            long b = fetch64(s, pos + len - 8);
            long c = rotate(b, 37) * kmul + a;
            long d = (rotate(a, 25) + b) * kmul;
            return hashLen16(c, d, kmul);
        }
        if (len >= 4) {
            long kmul = k2 + len * 2;
            long a = fetch32(s, pos + 0);
            return hashLen16((a << 3) + len, fetch32(s, pos + len - 4), kmul);
        }
        if (len > 0) {
            byte a = s[pos + 0];
            byte b = s[pos + (len >>> 1)];
            byte c = s[pos + len - 1];
            int y = staticCastToInt(a) + (staticCastToInt(b) << 8);
            int z = len + (staticCastToInt(c) << 2);
            return shiftMix(y * k2 ^ z * k0) * k2;
        }
        return k2;
    }

    private static long hashLen17to32(byte[] s, int pos, int len) {
        long mul = k2 + len * 2;
        long a = fetch64(s, pos + 0) * k1;
        long b = fetch64(s, pos + 8);
        long c = fetch64(s, pos + len - 8) * mul;
        long d = fetch64(s, pos + len - 16) * k2;
        return hashLen16(rotate(a + b, 43) + rotate(c, 30) + d,
                a + rotate(b + k2, 18) + c, mul);
    }


    public static Long reversalByte(Long l) {
        ByteBuffer buffer = ByteBuffer.allocate(8);

        byte[] array = buffer.putLong(0, l).array();
        byte[] newArr = new byte[array.length];
        for (int i = array.length - 1; i >= 0; i--) {
            newArr[array.length - i - 1] = array[i];
        }


        ByteBuffer buffer2 = ByteBuffer.wrap(newArr, 0, 8);
        /*if(littleEndian){
            // ByteBuffer.order(ByteOrder) 方法指定字节序,即大小端模式(BIG_ENDIAN/LITTLE_ENDIAN)
            // ByteBuffer 默认为大端(BIG_ENDIAN)模式
            buffer.order(ByteOrder.LITTLE_ENDIAN);
        }*/
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        return buffer.getLong();


    }

    private static long hashLen33to64(byte[] s, int pos, int len) {
        long mul = k2 + len * 2;
        long a = fetch64(s, pos) * k2;

        long b = fetch64(s, pos + 8);
        long c = fetch64(s, pos + len - 24);
        long d = fetch64(s, pos + len - 32);
        long e = fetch64(s, pos + 16) * k2;
        long f = fetch64(s, pos + 24) * 9;
        long g = fetch64(s, pos + len - 8);
        long h = fetch64(s, pos + len - 16) * mul;
        long u = rotate(a + g, 43) + (rotate(b, 30) + c) * 9;
        long v = ((a + g) ^ d) + f + 1;
        long w = reversalByte((u + v) * mul) + h;
        long x = rotate(e + f, 42) + c;
        long y = (reversalByte((v + w) * mul) + g) * mul;
        long z = e + f + c;
        a = reversalByte((x + z) * mul + y) + b;
        b = shiftMix((z + a) * mul + d + h) * mul;
        return b + x;
    }

    public static long cityHash64(String s){
        final byte[] bytes = s.getBytes(StandardCharsets.UTF_8);
        return cityHash64(bytes, 0, bytes.length);
    }
    public static long cityHash64(byte[] s, int pos, int len) {
        if (len <= 32) {
            if (len <= 16) {
                return hashLen0to16(s, pos, len);
            } else {
                return hashLen17to32(s, pos, len);
            }
        } else if (len <= 64) {
            return hashLen33to64(s, pos, len);
        }

        // For strings over 64 bytes we hash the end first, and then as we
        // loop we keep 56 bytes of state: v, w, x, y, and z.
        long x = fetch64(s, pos + len - 40);
        long y = fetch64(s, pos + len - 16) + fetch64(s, pos + len - 56);
        long z = hashLen16(fetch64(s, pos + len - 48) + len, fetch64(s, pos + len - 24));
        long[] v = weakHashLen32WithSeeds(s, pos + len - 64, len, z);
        long[] w = weakHashLen32WithSeeds(s, pos + len - 32, y + k1, x);
        x = x * k1 + fetch64(s, pos);

        // Decrease len to the nearest multiple of 64, and operate on 64-byte chunks.
        len = (len - 1) & ~63;
        do {
            x = rotate(x + y + v[0] + fetch64(s, pos + 8), 37) * k1;
            y = rotate(y + v[1] + fetch64(s, pos + 48), 42) * k1;
            x ^= w[1];
            y += v[0] + fetch64(s, pos + 40);
            z = rotate(z + w[0], 33) * k1;
            v = weakHashLen32WithSeeds(s, pos, v[1] * k1, x + w[0]);
            w = weakHashLen32WithSeeds(s, pos + 32, z + w[1], y + fetch64(s, pos + 16));
            long tmp = x;
            x = z;
            z = tmp;
            pos += 64;
            len -= 64;
        } while (len != 0);
        return hashLen16(hashLen16(v[0], w[0]) + shiftMix(y) * k1 + z,
                hashLen16(v[1], w[1]) + x);
    }


    private static long[] weakHashLen32WithSeeds(
            long w, long x, long y, long z,
            long a, long b) {

        a += w;
        b = rotate(b + a + z, 21);
        long c = a;
        a += x;
        a += y;
        b += rotate(a, 44);
        return new long[]{a + z, b + c};
    }

    private static long[] weakHashLen32WithSeeds(byte[] s, int pos, long a, long b) {
        return weakHashLen32WithSeeds(
                fetch64(s, pos + 0),
                fetch64(s, pos + 8),
                fetch64(s, pos + 16),
                fetch64(s, pos + 24),
                a,
                b
        );
    }

    private static long[] cityMurmur(byte[] s, int pos, int len, long seed0, long seed1) {

        long a = seed0;
        long b = seed1;
        long c = 0;
        long d = 0;

        int l = len - 16;
        if (l <= 0) {
            a = shiftMix(a * k1) * k1;
            c = b * k1 + hashLen0to16(s, pos, len);
            d = shiftMix(a + (len >= 8 ? fetch64(s, pos + 0) : c));
        } else {

            c = hashLen16(fetch64(s, pos + len - 8) + k1, a);
            d = hashLen16(b + len, c + fetch64(s, pos + len - 16));
            a += d;

            do {
                a ^= shiftMix(fetch64(s, pos + 0) * k1) * k1;
                a *= k1;
                b ^= a;
                c ^= shiftMix(fetch64(s, pos + 8) * k1) * k1;
                c *= k1;
                d ^= c;
                pos += 16;
                l -= 16;
            } while (l > 0);
        }

        a = hashLen16(a, c);
        b = hashLen16(d, b);

        return new long[]{a ^ b, hashLen16(b, a)};
    }

    private static long[] cityHash128WithSeed(byte[] s, int pos, int len, long seed0, long seed1) {
        if (len < 128) {
            return cityMurmur(s, pos, len, seed0, seed1);
        }

        long[] v = new long[2], w = new long[2];
        long x = seed0;
        long y = seed1;
        long z = k1 * len;
        v[0] = rotate(y ^ k1, 49) * k1 + fetch64(s, pos);
        v[1] = rotate(v[0], 42) * k1 + fetch64(s, pos + 8);
        w[0] = rotate(y + z, 35) * k1 + x;
        w[1] = rotate(x + fetch64(s, pos + 88), 53) * k1;

        // This is the same inner loop as CityHash64(), manually unrolled.
        do {
            x = rotate(x + y + v[0] + fetch64(s, pos + 16), 37) * k1;
            y = rotate(y + v[1] + fetch64(s, pos + 48), 42) * k1;

            x ^= w[1];
            y ^= v[0];

            z = rotate(z ^ w[0], 33);
            v = weakHashLen32WithSeeds(s, pos, v[1] * k1, x + w[0]);
            w = weakHashLen32WithSeeds(s, pos + 32, z + w[1], y);

            {
                long swap = z;
                z = x;
                x = swap;
            }
            pos += 64;
            x = rotate(x + y + v[0] + fetch64(s, pos + 16), 37) * k1;
            y = rotate(y + v[1] + fetch64(s, pos + 48), 42) * k1;
            x ^= w[1];
            y ^= v[0];
            z = rotate(z ^ w[0], 33);
            v = weakHashLen32WithSeeds(s, pos, v[1] * k1, x + w[0]);
            w = weakHashLen32WithSeeds(s, pos + 32, z + w[1], y);
            {
                long swap = z;
                z = x;
                x = swap;
            }
            pos += 64;
            len -= 128;
        } while (len >= 128);

        y += rotate(w[0], 37) * k0 + z;
        x += rotate(v[0] + z, 49) * k0;

        // If 0 < len < 128, hash up to 4 chunks of 32 bytes each from the end of s.
        for (int tail_done = 0; tail_done < len; ) {
            tail_done += 32;
            y = rotate(y - x, 42) * k0 + v[1];
            w[0] += fetch64(s, pos + len - tail_done + 16);
            x = rotate(x, 49) * k0 + w[0];
            w[0] += v[0];
            v = weakHashLen32WithSeeds(s, pos + len - tail_done, v[0], v[1]);
        }

        // At this point our 48 bytes of state should contain more than
        // enough information for a strong 128-bit hash.  We use two
        // different 48-byte-to-8-byte hashes to get a 16-byte final result.

        x = hashLen16(x, v[0]);
        y = hashLen16(y, w[0]);

        return new long[]{
                hashLen16(x + v[1], w[1]) + y,
                hashLen16(x + w[1], y + v[1])
        };
    }

    static long[] cityHash128(byte[] s, int pos, int len) {

        if (len >= 16) {
            return cityHash128WithSeed(
                    s, pos + 16,
                    len - 16,
                    fetch64(s, pos) ^ k3,
                    fetch64(s, pos + 8)
            );
        } else if (len >= 8) {
            return cityHash128WithSeed(
                    new byte[0], 0, 0,
                    fetch64(s, pos) ^ (len * k0),
                    fetch64(s, pos + len - 8) ^ k1
            );
        } else {
            return cityHash128WithSeed(s, pos, len, k0, k1);
        }
    }

}