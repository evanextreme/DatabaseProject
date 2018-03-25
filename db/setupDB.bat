set sqlLocation="C:\Program Files\MySQL\MySQL Workbench 6.3 CE\mysql.exe"
set sqlUsername="root"

%sqlLocation% -u %sqlUsername% -p < script.txt
%sqlLocation% bobby_tables -u %sqlUsername% -p < DDL.sql
%sqlLocation% bobby_tables -u %sqlUsername% -p < DML\discount.sql
%sqlLocation% bobby_tables -u %sqlUsername% -p < DML\brand.sql
%sqlLocation% bobby_tables -u %sqlUsername% -p < DML\vendor.sql
%sqlLocation% bobby_tables -u %sqlUsername% -p < DML\store.sql
%sqlLocation% bobby_tables -u %sqlUsername% -p < DML\customer.sql
%sqlLocation% bobby_tables -u %sqlUsername% -p < DML\product_type.sql
%sqlLocation% bobby_tables -u %sqlUsername% -p < DML\product.sql
%sqlLocation% bobby_tables -u %sqlUsername% -p < DML\shipment.sql
%sqlLocation% bobby_tables -u %sqlUsername% -p < DML\shipment_product.sql
%sqlLocation% bobby_tables -u %sqlUsername% -p < DML\transaction.sql
%sqlLocation% bobby_tables -u %sqlUsername% -p < DML\transaction_product.sql