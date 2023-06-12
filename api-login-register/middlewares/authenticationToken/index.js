const jwt = require('jsonwebtoken');

function authenticationToken(req, res, next) {
    const authHeader = req.headers["authorization"];
    if(!authHeader){
        return res.status(400).json({
            status: 'error',
            message: 'Token not found!',
        });
    }
    const token = authHeader.split(" ")[1];
    if(!token){
        return res.status(400).json({
            status: 'error',
            message: 'Token is required!',
        });
    }
    const decoded = jwt.verify(token, process.env.SECRET_KEY);
    const user = {
        id: decoded.id,
        username: decoded.username,
    };
    req.user = user;

    next();
}

module.exports = authenticationToken;