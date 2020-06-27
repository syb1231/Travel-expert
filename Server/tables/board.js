module.exports = (sequelize, DataTypes) => { 
    return sequelize.define('board', { 
        
        title : DataTypes.STRING(500),
        image : DataTypes.STRING(500),
        body : DataTypes.STRING(500),
        pref: DataTypes.INTEGER
         
    },{ timestamps: false,
        tableName: 'board'
    });
}
