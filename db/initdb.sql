CREATE TABLE Colourant(
  colourantId BIGINT AUTO_INCREMENT PRIMARY KEY,
  colourantCode VARCHAR(100) NOT NULL,
  colourantName VARCHAR(1000) NOT NULL,
  rbgHex VARCHAR(100) NOT NULL,
  density DOUBLE,
  pricePerUnit DOUBLE,
  surcharge INT,
  kind VARCHAR(100)
);

CREATE TABLE Role(
  roleId BIGINT AUTO_INCREMENT PRIMARY KEY,
  roleName VARCHAR(100) NOT NULL
);

CREATE TABLE User(
  userId BIGINT AUTO_INCREMENT PRIMARY KEY,
  userName VARCHAR(100) NOT NULL,
  password VARCHAR(100) NOT NULL,
  roleId BIGINT,
  FOREIGN KEY (roleId) REFERENCES Role(roleId) ON UPDATE CASCADE ON DELETE RESTRICT
);

CREATE TABLE Product(
  productId BIGINT AUTO_INCREMENT PRIMARY KEY,
  productCode VARCHAR(100) NOT NULL,
  productName VARCHAR(1000) NOT NULL,
  createdDate DATE,
  createBy BIGINT,
  FOREIGN KEY (createBy) REFERENCES User(userId) ON UPDATE CASCADE ON DELETE RESTRICT
);

CREATE TABLE Base(
  baseId BIGINT AUTO_INCREMENT PRIMARY KEY,
  baseCode VARCHAR(100) NOT NULL,
  baseName VARCHAR(1000) NOT NULL,
  createdDate DATE,
  createBy BIGINT,
  FOREIGN KEY (createBy) REFERENCES User(userId) ON UPDATE CASCADE ON DELETE RESTRICT
);

CREATE TABLE ProductBase(
  productBaseId BIGINT AUTO_INCREMENT PRIMARY KEY,
  productId BIGINT NOT NULL,
  baseId BIGINT NOT NULL,
  density DOUBLE,
  rbgHex VARCHAR(100) NOT NULL,
  createdDate DATE,
  createBy BIGINT,
  FOREIGN KEY (createBy) REFERENCES User(userId) ON UPDATE CASCADE ON DELETE RESTRICT,
  FOREIGN KEY (productId) REFERENCES Product(productId) ON UPDATE CASCADE ON DELETE RESTRICT,
  FOREIGN KEY (baseId) REFERENCES Base(baseId) ON UPDATE CASCADE ON DELETE RESTRICT
);

CREATE TABLE ProductBaseCan(
  productBaseCanId BIGINT AUTO_INCREMENT PRIMARY KEY,
  productBaseId BIGINT NOT NULL,
  can DOUBLE NOT NULL,
  unit VARCHAR(100),
  pricePerCan DOUBLE,
  barCode VARCHAR(1000),
  percentage INT,
  FOREIGN KEY (productBaseId) REFERENCES ProductBase(productBaseId) ON UPDATE CASCADE ON DELETE RESTRICT
);

CREATE TABLE Collection(
  collectionId BIGINT AUTO_INCREMENT PRIMARY KEY,
  collectionName VARCHAR(100) NOT NULL,
  description VARCHAR(1000),
  createdDate DATE,
  createBy BIGINT,
  FOREIGN KEY (createBy) REFERENCES User(userId) ON UPDATE CASCADE ON DELETE RESTRICT
);

CREATE TABLE Formula(
  formulaId BIGINT AUTO_INCREMENT PRIMARY KEY,
  formulaCode VARCHAR(100) NOT NULL,
  formulaName VARCHAR(1000) NOT NULL,
  collectionId BIGINT,
  createdDate DATE,
  createBy BIGINT,
  FOREIGN KEY (collectionId) REFERENCES Collection(collectionId) ON UPDATE CASCADE ON DELETE RESTRICT,
  FOREIGN KEY (createBy) REFERENCES User(userId) ON UPDATE CASCADE ON DELETE RESTRICT
);

CREATE TABLE FormulaColourant(
  formulaColourantId BIGINT AUTO_INCREMENT PRIMARY KEY,
  formulaId BIGINT NOT NULL,
  colourantId BIGINT,
  quantity DOUBLE NOT NULL,
  FOREIGN KEY (formulaId) REFERENCES Formula(formulaId) ON UPDATE CASCADE ON DELETE RESTRICT,
  FOREIGN KEY (colourantId) REFERENCES Colourant(colourantId) ON UPDATE CASCADE ON DELETE RESTRICT
);

CREATE TABLE FormulaProductBase(
  formulaProductBaseId BIGINT AUTO_INCREMENT PRIMARY KEY,
  formulaId BIGINT NOT NULL,
  productBaseId BIGINT NOT NULL,
  FOREIGN KEY (formulaId) REFERENCES Formula(formulaId) ON UPDATE CASCADE ON DELETE RESTRICT,
  FOREIGN KEY (productBaseId) REFERENCES ProductBase(productBaseId) ON UPDATE CASCADE ON DELETE RESTRICT
);

CREATE TABLE Users(
  userId BIGINT AUTO_INCREMENT PRIMARY KEY,
  userName VARCHAR(100) NOT NULL,
  password VARCHAR(20) NOT NULL
);


INSERT INTO Users (userName, password) VALUES ('admin', '123456');
INSERT INTO Users (userName, password) VALUES ('shop', '123456');
INSERT INTO Users (userName, password) VALUES ('operator', '123456');
INSERT INTO Users (userName, password) VALUES ('maintenance', '123456');