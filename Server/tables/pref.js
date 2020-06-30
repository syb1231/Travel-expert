module.exports = (sequelize, DataTypes) => { 
    return sequelize.define('pref', { 
        
        userid : DataTypes.STRING(50),
        pref_code : DataTypes.INTEGER
        
    },{ timestamps: false,
        tableName: 'pref'
    });
}
