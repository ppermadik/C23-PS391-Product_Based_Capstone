function customErrorHandler(error, req, res, next) {
    res.status(400).json({
        status: 'error',
        message: error.message,
    })
}

module.exports = customErrorHandler;