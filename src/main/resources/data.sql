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

INSERT INTO public.salaries(row_id,emp_id,salary) VALUES (1,1,10000);
INSERT INTO public.salaries(row_id,emp_id,salary) VALUES (2,2,5000);
INSERT INTO public.salaries(row_id,emp_id,salary) VALUES (3,3,3000);
INSERT INTO public.salaries(row_id,emp_id,salary) VALUES (4,4,7500);
INSERT INTO public.salaries(row_id,emp_id,salary) VALUES (5,5,2000);

INSERT INTO public.users(user_id,login,password,employee_id,role_id) VALUES (1,'admin','ISMvKXpXpadDiUoOSoAfww==',3,1);
INSERT INTO public.users(user_id,login,password,employee_id,role_id) VALUES (2,'user','7hHLsZBS5AsHqsDKBgwj7g==',1,2);
INSERT INTO public.users(user_id,login,password,employee_id,role_id) VALUES (3,'wadamiec','gZsGQ9a4ncm1ef38kJTyjg==',4,3);
INSERT INTO public.users(user_id,login,password,employee_id,role_id) VALUES (4,'knowakowska','NMyT7OC6nj9vI11K+XmxbA==',5,4);
INSERT INTO public.users(user_id,login,password,employee_id,role_id) VALUES (5,'pietnastak','2F/1fIvN3+mZChkJg5nbOg==',2,5);

INSERT INTO public.roles(role_id,parent_id,role_name) VALUES (1,0,'administrator');
INSERT INTO public.roles(role_id,parent_id,role_name) VALUES (2,1,'szef');
INSERT INTO public.roles(role_id,parent_id,role_name) VALUES (3,2,'ksiegowa');
INSERT INTO public.roles(role_id,parent_id,role_name) VALUES (4,2,'menadzer');
INSERT INTO public.roles(role_id,parent_id,role_name) VALUES (5,4,'pracownik');

INSERT INTO public.acl(acl_id,row_id,tab_name,user_id) VALUES (1,1,'salaries',5);
INSERT INTO public.acl(acl_id,row_id,tab_name,user_id) VALUES (2,2,'salaries',5);
INSERT INTO public.acl(acl_id,row_id,tab_name,user_id) VALUES (3,4,'salaries',5);