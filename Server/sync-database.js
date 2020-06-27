const tables = require('./tables')

module.exports = () => {
    return tables.sequelize.sync({force: true})
}