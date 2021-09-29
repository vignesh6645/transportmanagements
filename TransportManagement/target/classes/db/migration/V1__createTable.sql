CREATE TABLE User_vg(
    user_id Integer NOT NULL AUTO_INCREMENT,
    name varchar(255) NOT NULL,
    password varchar(255) NOT NULL,
    is_active int default 0,
    is_delete int default 0,
    created_at timestamp,
    modified_at timestamp,
    PRIMARY KEY(user_id)
);

CREATE TABLE loads(
   load_id INTEGER NOT NULL AUTO_INCREMENT,
   load_name varchar(255) NOT NULL,
   destination varchar(255) NOT NULL,
   is_active int default 0,
   is_delete int default 0,
   created_at timestamp,
   modified_at timestamp,
   fk_vehicle_id Integer,
   PRIMARY KEY(load_id)
);

CREATE TABLE vehicleType(
  vehicle_type_id INTEGER NOT NULL AUTO_INCREMENT,
  vehicleName varchar(255) NOT NULL,
  is_active int default 0,
  is_delete int default 0,
  created_at timestamp,
  modified_at timestamp,
  PRIMARY KEY(vehicle_type_id)
);

CREATE TABLE vehicle(
  vehicle_id INTEGER NOT NULL AUTO_INCREMENT,
  vehicle_name varchar(255) NOT NULL,
  registration_number int NOT NULL,
  is_active int default 0,
  is_delete int default 0,
  created_at timestamp,
  modified_at timestamp,
  fk_vehicle_type_id INTEGER,
  fk_user_id INTEGER,
  PRIMARY KEY(vehicle_id)
);

CREATE TABLE role(
  id INTEGER AUTO_INCREMENT,
  role_name varchar(255),
  PRIMARY KEY(id)
);

CREATE TABLE userrole(
  id int AUTO_INCREMENT,
  user_id_fk INTEGER NOT NULL,
  role_id_fk INTEGER NOT NULL,
  PRIMARY KEY(id)
);

alter table loads
    add foreign key (fk_vehicle_id) references vehicle(vehicle_id);

alter table vehicle
     add foreign key(fk_vehicle_type_id) references   vehicleType(vehicle_type_id),
     add foreign key(fk_user_id) references User_vg(user_id);

alter table userrole
     add foreign key(user_id_fk) references User_vg(user_id),
     add foreign key(role_id_fk) references role(id);





