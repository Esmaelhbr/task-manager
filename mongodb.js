// const mongodb = require('mongodb');
// const MongoClient = mongodb.MongoClient;
// const ObjectID = mongodb.ObjectID;

const {MongoClient, ObjectID} = require('mongodb');

const connectionUrl ="mongodb://127.0.0.1:27017";
const databaseName ="task-manager";


const id = new ObjectID();
console.log(id);
console.log(id.getTimestamp())

MongoClient.connect(connectionUrl, {useNewUrlParser: true}, 
	               (error, client) => {
	if (error){
		return console.log("Unable to connect to database");
	}

	const db = client.db(databaseName);



//delete
	// db.collection('users').deleteMany({
	// 	age: 13
	// }).then((result) => {
	// 	console.log(result)
	// }).catch((error) => {
	// 	console.log(error)
	// });

	db.collection('tasks').deleteOne({
		description: "play soccer"
	}).then((result) => {
		console.log("result")
	}).catch((error) => {
		console.log(error)
	})



	//update 
	// db.collection('users').updateOne({
 //        _id:ObjectID("617cb2c5e6302026bc41dd33")
	// }, {
	// 	$inc:{
	// 		age: 8
	// 	}
	// }).then((resut)=> {
	// 	console.log(result);

	// }).catch((error)=> {
	// 	console.log(error)

	// })


	// db.collection('tasks').updateMany({
	// 	status: false
	// },{
	// 	$set : {
	// 		status: true
	// 	}
		
	// }).then((result) => {
	// 	console.log(result)
	// }).catch((error) => {
	// 	console.log(error)
	// })



	// db.collection('users').findOne({_id: new ObjectID("617cc53378db6723508f6965")}, (error, user) => {
	// 	if(error){
	// 		return console.log('unable to feach')
	// 	}
	// 	console.log(user);
	// })

	// db.collection('users').find({age:13}).toArray((error, users) => {
	// 	console.log(users)
	// });
	// db.collection('users').find({age:13}).count((error, count) => {
	// 	console.log(count)
	// });

	// db.collection('tasks').findOne(
	// 	{
	// 		_id:new ObjectID("617d692a9266f1ee8ce31f68")
	// 	},(error,user) => {
	// 		if(error){
	// 			return console.log("error")
	// 		}
	// 		console.log(user)

	// 	})


	// db.collection("users").insertOne({
	// 	name :"Vikram",
	// 	age: "40"
	// }, (error, result) => {
	// 	if(error){
	// 		return console.log("unable to insert user");
	// 	}
	// 	console.log(result.ops);

	// });

	// db.collection('users').insertMany([
	// 	{
	// 	 name: "Jen",
	// 	 age: 28
	// 	},
	// 	{
	// 		name: 'mike',
	// 		age: 20
	// 	}


	// 	], (error, result) => {
	// 		if(error){
	// 			return console.log("unable to insert users");
	// 		}else{
	// 			console.log(result.ops)
	// 		}

	// 	})

	// db.collection("tasks").insertMany(
	// 	[
	// 		{
	// 			description: "play soccer",
	// 			status: false

	// 		},
	// 		{
	// 			description: "read a book",
	// 			status: false

	// 		},
	// 		{
	// 			description: "play guitar",
	// 			status: true

	// 		}

	// 	], (error, result) => {
	// 		if(error){
	// 			return console.log(error);
	// 		}else{
	// 		console.log(result.ops)
	// 		}
	// 	})




})


