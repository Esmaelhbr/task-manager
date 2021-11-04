require('../src/db/mongoose')
const User = require('../src/models/user')


//ObjectId("617cc53378db6723508f6965")

User.findOneAndUpdate("617cc53378db6723508f6965",{ age : 1}).then((user)=>{
	console.log(user)
	return User.countDocuments({age: 1})
}).then((result) => {
	console.log(result)
}).catch((e) =>{
	console.log(e)
});