const express = require('express');
const app = express();
const Joi = require('joi'); //class

var mongoose = require('mongoose');
mongoose.connect('mongodb://teojunjie:12345teojj@ds263460.mlab.com:63460/express-demo-database');

var db = mongoose.connection;
db.on('error',console.error.bind(console,'connection error:'));
db.once('open', function() {
  // we're connected!
  console.log('Connected to database successfully');
});

var userSchema = mongoose.Schema({
	name:String,
	birthday:String,
	email:String,
	friends:Number
},{collection:'MyUsers'});

var user = mongoose.model('MyUsers',userSchema);

console.log('My schema ' + mongoose.model('MyUsers').schema.eachPath(function(path){
	console.log(path);
}));



var jj = new user({name :'teojunjie',birthday:'14/03/1996',email:'teojunjie@gmail.com',friends:221});
console.log(jj.name);

jj.save(function(err,jj){
	if(err) return console.error(err);
	console.log('Successfully saved user');
})

var query = user.find({});
console.log('My query : '+query.exec(function(err,docs){
	console.log('My docs below :\n'+  docs +'\n\n' + 'Error : ' + err);
}));

app.use(express.json());
 
const courses = [
	{id:1,name:'course1'},
	{id:2,name:'course2'},
	{id:3,name:'course3'},
]

app.get('/',(req,res)=> {
	res.send('Hello World bitches');
})

app.get('/api/courses',(req,res)=>{
	res.send(courses);
})

app.get('/api/posts/:year/:month',(req,res)=>{
	res.send(req.query);
})

app.get('/api/courses/:id',(req,res)=>{
	const course = courses.find(c=> c.id===parseInt(req.params.id));
	if(!course){ //error 404
		return res.status(404).send('The course with the given ID was not found');
	}
	res.send(course);
})

app.post('/api/courses',(req,res)=>{

	const {error} = validateCourse(req.body) ;
	if(error){
		return res.status(400).send(error.details[0].message);

	}	

	const course = {
		id:courses.length +1,
		name: req.body.name
	};
	courses.push(course);
	res.send(course);
}) 

app.put('/api/courses/:id',(req,res)=> {
	const course = courses.find(c=> c.id === parseInt(req.params.id));
	if(!course){
		return res.status(404).send('The course with the given ID was not found');
	}

	const {error} = validateCourse(req.body) ;
	if(error){
		return res.status(400).send(error.details[0].message);
		
	}

	course.name = req.body.name;
	res.send(course);
});

app.delete('/api/courses/:id',(req,res)=>{
	const course = courses.find(c=> c.id === parseInt(req.params.id));
	if(!course){
		return res.status(404).send('The course with the given ID was not found');
	}

	const index = courses.indexOf(course);
	courses.splice(index,1); 

	res.send(course);


})

function validateCourse(course){
	const schema = {
		name:Joi.string().min(3).required()
	};


	return Joi.validate(course,schema);
}

//PORT
const port = process.env.PORT || 3000;


app.listen(port,()=> console.log(`Listening on port ${port}...`));