 var express        = require('express'),
    bodyParser     = require('body-parser'),
    http           = require('http'),
	request        = require('request'),
    app            = express(),
	token = '',
	senderName = '',
	moment = require('moment');

app.use(bodyParser.json());

// set port
app.set('port', process.env.PORT || 8080);

app.get('/', function(req, res) {
  if (req.query['hub.verify_token'] === 'xxxx') {
     res.send(req.query['hub.challenge']);
   } else {
     res.send('Error, wrong validation token');
   }
}); 

app.post('/',  function(req,res){
  res.send(req.body)
  if ("message" in req.body.entry[0].messaging[0]) {
  var senderid = req.body.entry[0].messaging[0].sender.id
  var message = req.body.entry[0].messaging[0].message.text
  var time = req.body.entry[0].time
  var dateTimeString = moment(time).format("DD-MM-YYYY HH:mm:ss");
          request({
        url: 'https://graph.facebook.com/v3.1/' + senderid  +  '?fields=name&access_token='+ token,
        method: 'GET'
        },
    function (error, response, body) {
        if (!error && response.statusCode == 200) {
            // Print out the response body
			senderName = JSON.parse(body)
			console.log(senderName.name)
        } else { 
            console.log('error',body);
        }
    });

  console.log("Message:" + message)
  console.log("Time:" + dateTimeString)
  }
  else {
  };
});

// start the server
http.createServer(app).listen(app.get('port'), function(){
  console.log('Express server listening on port ' + app.get('port'));
});