require('../src/db/mongoose')
const Task = require('../src/models/task')

//ObjectId("618322d18fc3121e3c8f23e9")
Task.findByIdAndDelete("61831f241fd35c228c63f851").then((task)=>{
	console.log(task)
	return Task.countDocuments({status: true});

}).then((taskResult) =>{
	console.log(taskResult)
}).catch((e) => {
	console.log(e)
})

