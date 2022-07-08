#db=summer_dev

## ListApps2 page publish 备注3
select a.id, a.status, a.num, a.num2
from demotable a
where true
and a.delete_status=:int_deleteStatus #v notNull Err_100010001000_xxxxxxx0
and a.delete_status in (:set_deleteStatus2); #v notNull notEmpty Err_100010001000_xxxxxxx1
