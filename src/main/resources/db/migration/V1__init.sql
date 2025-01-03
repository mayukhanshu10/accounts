CREATE TABLE IF NOT EXISTS `customer` (
                                          `id` BIGINT AUTO_INCREMENT NOT NULL,
                                          `name` varchar(100) NOT NULL,
    `email` varchar(100) NOT NULL,
    `mobile_number` varchar(20) NOT NULL,
    `created_at` date NOT NULL,
    `created_by` varchar(20) NOT NULL,
    `updated_at` date DEFAULT NULL,
    `updated_by` varchar(20) DEFAULT NULL,
    CONSTRAINT pk_category PRIMARY KEY (id)
    );

CREATE TABLE IF NOT EXISTS `account` (
                                          `customer_id` BIGINT NOT NULL,
                                          `account_number` BIGINT AUTO_INCREMENT NOT NULL,
                                          `account_type` varchar(100) NOT NULL,
    `branch_address` varchar(200) NOT NULL,
    `created_at` date NOT NULL,
    `created_by` varchar(20) NOT NULL,
    `updated_at` date DEFAULT NULL,
    `updated_by` varchar(20) DEFAULT NULL,
    CONSTRAINT pk_category PRIMARY KEY (account_number)
    );

ALTER TABLE account ADD CONSTRAINT account_FK FOREIGN KEY (customer_id) REFERENCES customer(id);
