#db=summer_dev
#import java.util.List;
## GetCount1 one unpublish 备注1
select count(1) 'count1'
from demotable a
where true
and a.delete_status=:int_deleteStatus #v notNull Err_100010001000_xxxxxxx comment:删除状态
#java if(0 == :int_deleteStatus && :int_deleteStatus>0 && null != :string_param2){
and a.delete_status=:int_deleteStatus #v notNull Err_100010001000_xxxxxxx comment:删除状态
#java }//}
#java if(0 == :int_deleteStatus && :int_deleteStatus>0 && null != :string_param2){
and a.delete_status=:int_deleteStatus; #
#java }


