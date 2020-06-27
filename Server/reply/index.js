const express = require('express');
const router = express.Router();
const tables = require('../tables')

module.exports = router;

router.post('/upload', (req, res) => {
    body = req.body

    tables.Board.create({
        title: body.title,
        body: body.body,
        author: body.author
    }).then( () => {
        res.status(200).json({result : 'Success'})
    }).catch( (err) => {
        console.log(err)

        res.status(404).json({result : 'Fail'})
    })
})

router.get('/loadAll', (req, res) => {

    tables.Board.findAll().then( (results) => {
        res.status(200).json(results)
    }).catch( (err) => {
        console.log(err)

        res.status(404).json({result : 'Fail'})
    })
})

router.get('/loadAll/:id', (req, res) => {

    id = req.params.id

    tables.Board.findOne({
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