const mongodb = require('mongodb');
const MongoClient = mongodb.MongoClient;

const connectionUrl ="mongodb://127.0.0.1:27017";
const databaseName ="task-manager";

MongoClient.connect(connectionUrl, {useNewUrlParser: true}, 
	               (error, client) => {
	if (error){
		return console.log("Unable to connect to database");
	}
	const db = client.db(databaseName);
	// db.collection("users").insertOne({
	// 	name :"James",
	// 	age: "13"
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

	db.collection("tasks").insertMany(
		[
			{
				description: "play soccer",
				status: false

			},
			{
				description: "read a book",
				status: false

			},
			{
				description: "play guitar",
				status: true

			}

		], (error, result) => {
			if(error){
				return console.log(error);
			}else{
			console.log(result.ops)
			}
		})


})


