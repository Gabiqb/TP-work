drop procedure if exists getdata;
CREATE PROCEDURE getdata(email1 varchar(50), parola1 varchar(100), out ok int)
begin
		select @count1:= count(email) from users u where email1=u.email;
			select @email:= email from users u where u.email=email1;
			select @count2:= count(parola) from users u where parola1=u.parola and @email=email;
			if(@count1 =0) then 
					set ok=0;
			elseif(@count2 =0) then
					set ok=0;
		  else
			    set ok=1;
			end if;
end;
 
drop procedure if exists updateUser;
create procedure updateUser(id1 int,nume1 varchar(50),prenume1 varchar(50),nr_tel1 varchar(20),email1 varchar(50),parola1 varchar(50),adresa1 varchar(100),functie1 varchar(50))
begin

			update users
			set nume:=nume1,prenume:=prenume1,nr_tel:=nr_tel1,email:=email1,parola:=parola1,adresa:=adresa1,functie:=functie1 where id=id1;

end; 


drop procedure if exists insertUser;
create procedure insertUser(nume1 varchar(50),prenume1 varchar(50),nr_tel1 varchar(20),email1 varchar(50),parola1 varchar(50),adresa1 varchar(100))
begin
		select @id:=max(id) +1	from users;
		insert into users values(@id,nume1,prenume1,nr_tel1,email1,parola1,adresa1,'client');

end; 

drop procedure if exists insertUser2;
create procedure insertUser2(id1 int,nume1 varchar(50),prenume1 varchar(50),nr_tel1 varchar(20),email1 varchar(50),parola1 varchar(50),adresa1 varchar(100),functie1 varchar(50))
begin
		insert into users values(id1,nume1,prenume1,nr_tel1,email1,parola1,adresa1,functie1);

end; 


drop procedure if exists stergeUser;
CREATE DEFINER=`root`@`localhost` PROCEDURE `stergeUser`(id1 int)
begin
      select @id:= id from users u where u.id=id1 into @id;
		 if(@id is not null) then
			delete from users u where @id=u.id;
			end if;

end;
 
drop procedure if exists insertProdus;
create procedure insertProdus(id1 int,denumire1 varchar(100),rating1 decimal(5,2),calorii1 int,proteine1 int,grasimi1 int,carbohidrati1 int,pret1 int)
begin
		insert into products values(id1,denumire1,rating1,calorii1,proteine1,grasimi1,carbohidrati1,pret1);
		
end; 

drop procedure if exists updateProdus;
create procedure updateProdus(id1 int, denumire1 varchar(100),rating1 decimal(5,2),calorii1 int,proteine1 int,grasimi1 int,carbohidrati1 int,pret1 int)
begin
			update products
			set denumire:=denumire1,rating:=rating1,calorii:=calorii1,proteine:=proteine1,grasimi:=grasimi1,carbohidrati:=carbohidrati1,pret:=pret1 where id=id1;
end; 


drop procedure if exists stergeProdus;
CREATE DEFINER=`root`@`localhost` PROCEDURE `stergeProdus`(id1 int)
begin
      select @id:= id from products u where u.id=id1 into @id;
		 if(@id is not null) then
			delete from products u where @id=u.id;
		 end if;

end;

drop procedure if exists insertOrder;
CREATE DEFINER=`root`@`localhost` PROCEDURE `insertOrder`(id1 bigint,nume_client1 varchar(50),prenume_client1 varchar(50),produse1 TEXT,cantitate1 TEXT,adresa1 varchar(100),data_eliberare1 timestamp,detalii1 TEXT,nr_tel1 varchar(20),total1 int)
begin
		
			insert into orders values(id1,nume_client1,prenume_client1,nr_tel1,produse1,cantitate1,total1,adresa1,data_eliberare1,detalii1);
		
end;

drop procedure if exists updateOrder;
create procedure updateOrder(id1 int,nume_client1 varchar(50), prenume_client1 varchar(50),nume_agent1 varchar(50),prenume_agent1 varchar(50),produse1 text,cantitate1 int,adresa1 varchar(100),data_eliberare1 timestamp,detalii1 TEXT,nr_tel1 varchar(20))
begin
			update orders
			set nume_client:=nume_client1,prenume_client:=prenume_client1,nume_agent:=nume_agent1,prenume_agent:=prenume_agent1,produse:=produse1,adresa:=adresa1,data_eliberare:=data_eliberare1,detalii:=detalii1,nr_tel:=nr_tel1,cantitate:=cantitate1 where id=id1;
end; 

 


drop procedure if exists stergeOrder;
CREATE DEFINER=`root`@`localhost` PROCEDURE `stergeOrder`(id1 int)
begin
      select @id:= id from orders u where u.id=id1 into @id;
		 if(@id is not null) then
			delete from orders u where @id=u.id;
		 end if;

end;

 
