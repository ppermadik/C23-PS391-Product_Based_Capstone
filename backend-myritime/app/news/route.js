const express = require('express');
const { handlerGetAllNews } = require('./handler');
const authenticationToken = require('../../middlewares/authenticationToken');
const router = express.Router();

router.get('/', authenticationToken, handlerGetAllNews);

module.exports = router;
