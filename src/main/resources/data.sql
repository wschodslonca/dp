DELETE FROM public.employees;
DELETE FROM public.salaries;
DELETE FROM public.roles;
DELETE FROM public.acl;
DELETE FROM public.users;
INSERT INTO public.employees(employee_id,first_name,last_name) VALUES (1,'Jan','Kowalski');
INSERT INTO public.employees(employee_id,first_name,last_name) VALUES (2,'Krzysztof','Nowak');
INSERT INTO public.employees(employee_id,first_name,last_name) VALUES (3,'Maria','Kowalska');
INSERT INTO public.employees(employee_id,first_name,last_name) VALUES (4,'Wojciech','Adamiec');
INSERT INTO public.employees(employee_id,first_name,last_name) VALUES (5,'Katarzyna','Nowakowska');

INSERT INTO public.salaries(row_id,employee_id,salary) VALUES (1,1,10000);
INSERT INTO public.salaries(row_id,employee_id,salary) VALUES (2,2,5000);
INSERT INTO public.salaries(row_id,employee_id,salary) VALUES (3,3,3000);
INSERT INTO public.salaries(row_id,employee_id,salary) VALUES (4,4,7500);
INSERT INTO public.salaries(row_id,employee_id,salary) VALUES (5,5,2000);

INSERT INTO public.users(user_id,login,password,employee_id,role_id) VALUES (1,'mkowalska','password1',3,1);
INSERT INTO public.users(user_id,login,password,employee_id,role_id) VALUES (2,'jkowalski','password2',1,2);
INSERT INTO public.users(user_id,login,password,employee_id,role_id) VALUES (3,'wadamiec','password3',4,3);
INSERT INTO public.users(user_id,login,password,employee_id,role_id) VALUES (4,'knowakowska','password4',5,4);

INSERT INTO public.roles(role_id,role_name) VALUES (1,'administrator');
INSERT INTO public.roles(role_id,role_name) VALUES (2,'szef');
INSERT INTO public.roles(role_id,role_name) VALUES (3,'ksiegowa');
INSERT INTO public.roles(role_id,role_name) VALUES (4,'menadzer');
INSERT INTO public.roles(role_id,role_name) VALUES (5,'pracownik');

INSERT INTO public.acl(acl_id,row_id,tab_name,user_id) VALUES (1,1,'public.salaries',1);
INSERT INTO public.acl(acl_id,row_id,tab_name,user_id) VALUES (2,1,'public.salaries',3);
INSERT INTO public.acl(acl_id,row_id,tab_name,user_id) VALUES (3,1,'public.salaries',4);
INSERT INTO public.acl(acl_id,row_id,tab_name,user_id) VALUES (4,2,'public.salaries',1);
INSERT INTO public.acl(acl_id,row_id,tab_name,user_id) VALUES (5,2,'public.salaries',2);
INSERT INTO public.acl(acl_id,row_id,tab_name,user_id) VALUES (6,2,'public.salaries',3);
INSERT INTO public.acl(acl_id,row_id,tab_name,user_id) VALUES (7,2,'public.salaries',4);
INSERT INTO public.acl(acl_id,row_id,tab_name,user_id) VALUES (8,3,'public.salaries',3);
INSERT INTO public.acl(acl_id,row_id,tab_name,user_id) VALUES (9,3,'public.salaries',4);
INSERT INTO public.acl(acl_id,row_id,tab_name,user_id) VALUES (10,4,'public.salaries',4);