const { user: User } = require('../../models');
const bcrypt = require('bcrypt');
const validateUserPayload = require('../../validator/user');
const generateAccessToken  = require('../../utils/tokenManager');

module.exports = {
    handlerGetUsers: async (req, res) => {
        try {
            const user = await User.findAll();
            res.status(200).json({
                status: 'success',
                data: user,
            });  
        } catch (error) {
            res.status(500).json({
                status: 'error',
                message: error.message,
            });
        }
    },
    handlerGetUserById: async (req, res, next) => {
        try {
            const { id } = req.params;
            const user = await User.findOne({
                where: {
                    id: id,
                },
            });
            if (!user) {
                return res.status(400).json({
                    status: 'error',
                    message: 'User not found',
                })
            }
            res.status(200).json({
                status: 'success',
                data: user,
            });
        } catch (error) {
            next(error);
        }
    },
    handlerRegisterUser: async (req, res) => {
        try {
            const { username, password, email, fullName } = req.body;
            validateUserPayload(req.body);
            const hashPassword = await bcrypt.hash(password, 10);
            const user = await User.create({
                username,
                password: hashPassword,
                email,
                fullName,
            });
            console.log(user);
            res.status(201).json({
                status: 'success',
                data: user,
            });
        } catch (error) {
            res.status(500).json({
                status: 'error',
                message: error.message,
            });
        }
    },
    handlerLoginUser: async(req, res) => {
        try {
            const { username, password } = req.body;
            const user = await User.findOne({
                where: {
                    username,
                }
            });
            if (!user) {
                return res.status(400).json({
                    status: 'error',
                    message: 'User not found',
                });
            }
            const isValidPassword = bcrypt.compareSync(password, user.password)
            if(!isValidPassword){
                return res.status(400).json({
                    status: 'error',
                    message: 'Invalid username or password!',
                });
            }
            const accesToken = generateAccessToken({
                id: user.id,
                fullName: user.fullName,
                email: user.email,
            });

            res.status(200).json({
                status: 'success',
                messageL: 'Login success',
                data: {
                    user,
                    accesToken,
                }
            });
        } catch (error) {
            res.status(500).json({
                status: 'error',
                message: error.message,
            });
        }
    }
}