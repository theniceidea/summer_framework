#db=summer_dev

## ListApps list publish 备注2
select a.id, a.status, a.num, a.num2
from demotable a
where true
and a.delete_status=:int_deleteStatus; #v notNull Err_100010001000_xxxxxxx comment:删除标志
