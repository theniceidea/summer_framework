#db=summer_dev

## GetApp one publish 备注1
select a.id 'abc', a.status, a.num, a.num2, a.delete_status
from demotable a
where true
and a.delete_status=:int_deleteStatus #v notNull Err_100010001000_xxxxxxx comment:删除状态
and a.delete_status=:int_deleteStatus
and a.delete_status is null
and a.delete_status in (:list_ds); #v notEmpty Err_100010001000_xxxxxxx comment:删除状态
