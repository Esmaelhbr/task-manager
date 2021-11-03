const mongoose = require('mongoose')
const validator = require('validator')

mongoose.connect('mongodb://127.0.0.1:27017/task-manager-api',{
	useNewUrlParser: true,
	useCreateIndex: true
})


// const User = mongoose.model('User',
// 	{
// 		name: {
// 			type: String,
// 			required: true,
// 			trim: true
// 		},
// 		email:{
// 			type: String,
// 			required: true,
// 			trim: true,
// 			lowercase: true,
// 			validate(value){
// 				if(!validator.isEmail(value)){
// 					throw new Error('email is not valid')
// 				}
// 			}
// 		},
// 		password:{
// 			type: String,
// 			required: true,
// 			trim: true,
// 			minlength: 7,
// 			validate(value){
// 				if( value.includes("password")){
// 					throw new Error("Invalid password cannot contain password")
// 				}

// 			}
// 		},
// 		age: {
// 			type: Number,
// 			default: 0,
// 			validate(value){
// 				if(value <0 ){
// 					throw new Error("Age must be a positive number")
// 				}
// 			}
// 		}
// 	})

// const me = new User({
// 	name: "  Jackson  ",
// 	email: 'JohnnyB@hotmail.com    ',
// 	password: "ease"
// })

// me.save().then(()=> {
// 	console.log(me)
// }).catch((error) =>{
// 	console.log(error);
// })


const Task = mongoose.model("Task",{
	
	description:{
		type: String,
		trim: true,
		required: true,


	},
	status:{
		type:  Boolean,
		default: false
	}
})

const taskOne = new Task({
	
	description: "Dance with friends",
	status: false
	
})


taskOne.save().then(() => {
	console.log(taskOne)
}).catch((error) => {
	console.log(error)
})
