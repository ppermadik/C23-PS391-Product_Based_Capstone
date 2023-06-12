const userSchema = require('./schema');

function validateUserPayload(payload){
    const validationResult = userSchema.validate(payload);

    if(validationResult.error){
        throw new Error(validationResult.error.message);
    }
}

module.exports = validateUserPayload;