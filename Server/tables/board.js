module.exports = (sequelize, DataTypes) => { 
    return sequelize.define('board', { 
        
        title : DataTypes.STRING(500),
        image : DataTypes.INTEGER,
        body : DataTypes.STRING(500),
        pref: DataTypes.INTEGER
         
    },{ timestamps: false,
        tableName: 'board'
    });
}
