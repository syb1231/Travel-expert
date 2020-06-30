const express = require('express');
const router = express.Router();
const tables = require('../tables')

module.exports = router;

router.post('/add', (req, res) => {
    userid = req.body.userid
    code = req.body.code

    tables.Pref.create({
        userid : userid,
        pref_code : code
    }).then( () => {
        res.status(200).json({result : "Success"})
    }).catch( (err) => {
        console.log(err)

        res.status(404).json({result : 'Fail'})
    })

})

router.post('/delete', (req, res) => {
    userid = req.body.userid
    code = req.body.code

    tables.Pref.destroy({
        where: {
            userid : userid,
            pref_code : code
        }
    }).then( () => {
        res.status(200).json({result : "Success"})
    }).catch( (err) => {
        console.log(err)

        res.status(404).json({result : 'Fail'})
    })
})

router.get('/index', (req, res) => {
    
    tables.Pref.findAll().then( (results) => {
        res.status(200).json(results)
    }).catch( (err) => {
        res.status(404).json({result: 'Fail'})
    })
})

router.post('/index', (req, res) => {

    userid = req.body.userid
    
    tables.Pref.findAll({
        attributes: ['pref_code'],
        where: {
            userid: userid
        }
    }).then( (results) => {
        res.status(200).json(results)
    }).catch( (err) => {
        res.status(404).json({result: 'Fail'})
    })
})