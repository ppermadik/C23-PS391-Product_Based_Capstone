const Joi = require('joi');
const userSchema = Joi.object({
    username: Joi.string().required(),
    password : Joi.string().min(8).alphanum().required(),
    fullName: Joi.string().required(),
    email: Joi.string().email().required(),
});

module.exports = userSchema;