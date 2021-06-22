CREATE TABLE IF NOT EXISTS account (
    id UUID NOT NULL,
    document_number VARCHAR(20) NOT null,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL
);

CREATE TABLE IF NOT EXISTS operation (
    id int NOT NULL,
    description VARCHAR(50) NOT null
);

CREATE TABLE IF NOT EXISTS transaction (
    id UUID NOT NULL,
    account_id UUID NOT NULL,
    operation_type_id int not null,
    operation_type_id smallint NOT null,
    amount decimal NOT NULL,
    event_date TIMESTAMP NOT null,
    
    constraint fk_account
    	foreign key(account_id)
    		references account(id),
    
    constraint fk_operation
    	foreign key(operation_type_id)
    		references operation(id)
);

insert into operation 
	values 
		(1, 'COMPRA A VISTA'),
		(2, 'COMPRA PARCELADA'),
		(3, 'SAQUE'),
		(4, 'PAGAMENTO');