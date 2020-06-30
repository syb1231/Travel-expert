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

router.post('/loadAll', (req, res) => {

    userid = req.body.userid
    var pref_arr = new Array()
    

    tables.Pref.findAll({
        where: {
            userid: userid
        }
    }).then( (results) => {

        for(var i = 0; i < results.length; i++){
            pref_arr.push(results[i].dataValues.pref_code)
        }

        tables.Board.findAll().then( (results1) => {

            var arr1 = new Array()

            var arr2 = new Array() // pref
            var arr3 = new Array() // except

            n = results1.length

            for(var i = 0; i< n; i++){
                arr1.push(results1[i].dataValues)
            }

            while(n != 0){
                x = arr1.pop()

                if(pref_arr.indexOf(x.pref) != -1){
                    arr2.push(x)
                }else{
                    arr3.push(x)
                }                

                n = arr1.length
            }
            
            result = arr2.concat(arr3)

            res.status(200).json(result)
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