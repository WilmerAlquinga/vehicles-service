/*==============================================================*/
/* Table: VEHICLE                                               */
/*==============================================================*/
CREATE TABLE vehicle
(
    vehicle_id            SERIAL         NOT NULL,
    vehicle_plate         VARCHAR(16)    NOT NULL UNIQUE,
    vehicle_model         VARCHAR(32)    NOT NULL,
    vehicle_year          INT2           NOT NULL,
    vehicle_purchase_date DATE,
    vehicle_price         NUMERIC(12, 4) NOT NULL,
    vehicle_observation   VARCHAR(256)   NULL,
    vehicle_created_at    TIMESTAMP,
    vehicle_updated_at    TIMESTAMP,
    vehicle_deleted_at    TIMESTAMP      NULL,
    CONSTRAINT PK_vehicle PRIMARY KEY (vehicle_id)
);

COMMENT
    ON TABLE vehicle IS 'Table for storing all vehicles of vehicles';
