const express = require('express');
const router = express.Router();
const tables = require('../tables');
const board = require('../tables/board');

module.exports = router;

router.post('/upload', (req, res) => {
    body = req.body

    tables.Reply.create({
        board_id: body.board_id,
        body: body.body,
        author: body.author
    }).then( () => {
        res.status(200).json({result : 'Success'})
    }).catch( (err) => {
        console.log(err)

        res.status(404).json({result : 'Fail'})
    })


})

router.get('/loadAll/:board_id', (req, res) => {

    board_id = req.params.board_id

    tables.Reply.findAll({
        where: {
            board_id: board_id
        }
    }).then( (results) => {
        res.status(200).json(results)
    }).catch( (err) => {
        console.log(err)

        res.status(404).json({result : 'Fail'})
    })
})