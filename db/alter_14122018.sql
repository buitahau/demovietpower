ALTER TABLE Formula ADD COLUMN baseOnCan DOUBLE;
UPDATE Formula Set baseOnCan = 1;
CREATE TABLE Machine(
  machineId BIGINT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  code VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE MachineColourant(
  machineColourantId BIGINT AUTO_INCREMENT PRIMARY KEY,
  machineId BIGINT NOT NULL,
  colourantId BIGINT NOT NULL,
  quantity DOUBLE,
  CONSTRAINT FK_MachineColourant_Machine FOREIGN KEY (machineId) REFERENCES Machine(machineId),
  CONSTRAINT FK_MachineColourant_Colourant FOREIGN KEY (colourantId) REFERENCES Colourant(colourantId)
);

CREATE TABLE MachineColourantLog(
  machineColourantLogId BIGINT AUTO_INCREMENT PRIMARY KEY,
  machineColourantId BIGINT NOT NULL,
  action VARCHAR(100),
  quantity DOUBLE ,
  createdDate DATETIME,
  CONSTRAINT FK_MachineColourantLog_MachineColourant FOREIGN KEY (machineColourantId) REFERENCES MachineColourant(machineColourantId)
);

CREATE TABLE MachineFormulaProductBase(
  machineFormulaId BIGINT AUTO_INCREMENT PRIMARY KEY,
  machineId BIGINT NOT NULL,
  formulaProductBaseId BIGINT NOT NULL,
  userId BIGINT,
  quantity DOUBLE,
  createdDate DATETIME,
  CONSTRAINT FK_MachineFormula_Machine FOREIGN KEY (machineId) REFERENCES Machine(machineId),
  CONSTRAINT FK_MachineFormula_FormulaProductBase FOREIGN KEY (formulaProductBaseId) REFERENCES FormulaProductBase(formulaProductBaseId),
  CONSTRAINT FK_MachineFormula_User FOREIGN KEY (userId) REFERENCES User(userId)
);

-- Remove and rename foreign key
ALTER TABLE Product DROP FOREIGN KEY product_ibfk_1;
ALTER TABLE Product DROP COLUMN createBy;

ALTER TABLE Base DROP FOREIGN KEY base_ibfk_1;
ALTER TABLE Base DROP COLUMN createBy;

ALTER TABLE ProductBase DROP FOREIGN KEY productbase_ibfk_1;
ALTER TABLE ProductBase DROP FOREIGN KEY productbase_ibfk_2;
ALTER TABLE ProductBase DROP FOREIGN KEY productbase_ibfk_3;
ALTER TABLE ProductBase DROP COLUMN createBy;
ALTER TABLE ProductBase ADD CONSTRAINT FK_ProductBase_Product FOREIGN KEY (productId) REFERENCES Product(productId);
ALTER TABLE ProductBase ADD CONSTRAINT FK_ProductBase_Base FOREIGN KEY (baseId) REFERENCES Base(baseId);

ALTER TABLE Collection DROP FOREIGN KEY collection_ibfk_1;
ALTER TABLE Collection DROP COLUMN createBy;

ALTER TABLE Formula DROP FOREIGN KEY formula_ibfk_1;
ALTER TABLE Formula DROP FOREIGN KEY formula_ibfk_2;
ALTER TABLE Formula DROP COLUMN createBy;
ALTER TABLE Formula ADD CONSTRAINT FK_Formula_Collection FOREIGN KEY (collectionId) REFERENCES Collection(collectionId);

ALTER TABLE FormulaColourant DROP FOREIGN KEY formulacolourant_ibfk_1;
ALTER TABLE FormulaColourant DROP FOREIGN KEY formulacolourant_ibfk_2;
ALTER TABLE FormulaColourant ADD CONSTRAINT FK_FormulaColourant_Formula FOREIGN KEY (formulaId) REFERENCES Formula(formulaId);
ALTER TABLE FormulaColourant ADD CONSTRAINT FK_FormulaColourant_Colourant FOREIGN KEY (colourantId) REFERENCES Colourant(colourantId);

ALTER TABLE FormulaProductBase DROP FOREIGN KEY formulaproductbase_ibfk_1;
ALTER TABLE FormulaProductBase DROP FOREIGN KEY formulaproductbase_ibfk_2;
ALTER TABLE FormulaProductBase ADD CONSTRAINT FK_FormulaProductBase_Formula FOREIGN KEY (formulaId) REFERENCES Formula(formulaId);
ALTER TABLE FormulaProductBase ADD CONSTRAINT FK_FormulaProductBase_ProductBase FOREIGN KEY (productBaseId) REFERENCES ProductBase(productBaseId);

ALTER TABLE ProductBaseCan DROP FOREIGN KEY productbasecan_ibfk_1;
ALTER TABLE ProductBaseCan ADD CONSTRAINT FK_ProductBaseCan_ProductBase FOREIGN KEY (productBaseId) REFERENCES ProductBase(productBaseId);

ALTER TABLE User DROP FOREIGN KEY user_ibfk_1;
ALTER TABLE User ADD CONSTRAINT FK_User_Role FOREIGN KEY (roleId) REFERENCES Role(roleId);

-- Add machine constraint
ALTER TABLE User ADD COLUMN machineId BIGINT;
ALTER TABLE User ADD CONSTRAINT FK_User_Machine FOREIGN KEY (machineId) REFERENCES Machine(machineId);

ALTER TABLE Formula ADD COLUMN machineId BIGINT;
ALTER TABLE Formula ADD CONSTRAINT FK_Formula_Machine FOREIGN KEY (machineId) REFERENCES Machine(machineId);

ALTER TABLE Collection ADD COLUMN machineId BIGINT;
ALTER TABLE Collection ADD CONSTRAINT FK_Collection_Machine FOREIGN KEY (machineId) REFERENCES Machine(machineId);

-- Init db test
DELETE FROM User;
DELETE FROM Role;
DELETE FROM Machine;

INSERT INTO Role (roleName) VALUES ('ADMIN');
INSERT INTO Role (roleName) VALUES ('SHOP');
INSERT INTO Role (roleName) VALUES ('OPERATOR');
INSERT INTO Role (roleName) VALUES ('MAINTENANCE');

INSERT INTO User (userName, password, roleId) VALUES ('admin', '123456', (SELECT roleId FROM Role WHERE roleName = 'ADMIN'));
INSERT INTO User (userName, password, roleId) VALUES ('shop', '123456', (SELECT roleId FROM Role WHERE roleName = 'SHOP'));
INSERT INTO User (userName, password, roleId) VALUES ('operator', '123456', (SELECT roleId FROM Role WHERE roleName = 'OPERATOR'));
INSERT INTO User (userName, password, roleId) VALUES ('maintenance', '123456', (SELECT roleId FROM Role WHERE roleName = 'MAINTENANCE'));

INSERT INTO Machine (name, code) VALUES ('M1', 'ABCDEF');
UPDATE User SET machineId = (SELECT machineId FROM Machine WHERE name = 'M1');


ALTER TABLE Machine ADD COLUMN minQuantity DOUBLE;
ALTER TABLE Machine ADD COLUMN maxQuantity DOUBLE;

UPDATE Machine SET minQuantity = 500, maxQuantity = 3000;

ALTER TABLE MachineFormulaProductBase ADD COLUMN status VARCHAR(100);

ALTER TABLE MachineFormulaProductBase ADD COLUMN finishedDate DATE;
