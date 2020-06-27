const express = require('express');
const router = express.Router();
const tables = require('../tables')
const { Op } = require('sequelize')

module.exports = router;

router.post('/upload', (req, res) => {
    body = req.body


    tables.Board.create({
        title: body.title,
        image: body.image,
        body: body.body,
        pref: body.pref
    }).then( () => {
        res.status(200).json({result : 'Success'})
    }).catch( (err) => {
        console.log(err)

        res.status(404).json({result : 'Fail'})
    })
})

router.get('/loadAll/:pref', (req, res) => {

    pref = req.params.pref

    var result = new Array()

    tables.Board.findAll({
        where: {
            pref: pref
        }
    }).then( (results1) => {

        for(var i = 0; i < results1.length; i++){
            result.push(results1[i].dataValues)
        }

        tables.Board.findAll({
            where: {
                pref: { [Op.not] : pref }
            }
        }).then( (results2) => {
            
            for(var i = 0; i < results2.length; i++){
                result.push(results2[i].dataValues)
            }

            console.log(result)

            res.status(200).json(result)
        }).catch( (err) => {
            console.log(err)
    
            res.status(404).json({result : 'Fail'})
        })
    }).catch( (err) => {
        console.log(err)

        res.status(404).json({result : 'Fail'})
    })

})

router.get('/loadOne/:id', (req, res) => {

    id = req.params.id

    tables.Board.findAll({
        where: {
            id: id
        }
    }).then( (result) => {
        res.status(200).json(result)
    }).catch( (err) => {
        console.log(err)

        res.status(404).json({result : 'Fail'})
    })
})