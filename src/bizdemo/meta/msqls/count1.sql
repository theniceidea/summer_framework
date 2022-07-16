#db=summer_dev

## GetCount1 one unpublish 备注1
select count(1) 'count1'
from demotable a
where true
and a.delete_status=:int_deleteStatus #v notNull Err_100010001000_xxxxxxx comment:删除状态
