const express = require('express')
const app = express()
const bodyParser = require('body-parser')
const port = 3000
const syncDatabase = require('./sync-database')

app.use(bodyParser.json())
app.use(bodyParser.urlencoded({extended: true}))

app.use('/board', require('./board'))
app.use('/reply', require('./reply'))
app.use('/pref', require('./pref'))

app.listen(port, () => {
    console.log('Connected port', port)

    syncDatabase().then( () => {
        console.log('Database Sync')
    })
})