module.exports = (sequelize, DataTypes) => { 
    return sequelize.define('reply', { 
        
        board_id : DataTypes.INTEGER,
        body : DataTypes.STRING(500),
        author : DataTypes.STRING(32)
        
    },{ timestamps: false,
        tableName: 'reply'
    });
}
