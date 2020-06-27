var path = require('path');
var Sequelize = require('sequelize');

var env = process.env.NODE_ENV || 'development'; 
var config = require(path.join(__dirname, '.', 'config.json'))[env]; 
var db = {};

var sequelize =new Sequelize(config.database, config.username, config.password, config);

db.sequelize = sequelize;
db.Sequelize = Sequelize;

db.Board = require('./board')(sequelize, Sequelize)
db.Reply = require('./reply')(sequelize, Sequelize)

module.exports = db;