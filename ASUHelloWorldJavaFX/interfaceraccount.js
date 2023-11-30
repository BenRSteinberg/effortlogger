var fs = require('fs');
var fetch = require('node-fetch');
var CloudKit = require('./cloudkit');

var thisdate = "";
CloudKit.configure({
    services: {
        fetch: fetch
    },
    containers: [{
        containerIdentifier: 'iCloud.equationiq',
        environment: 'development',
        serverToServerKeyAuth: {
            keyID: '8d40f00bb14d5b82100a118a7e13d713eaa877cb90bd666e72ee8fa449c66640',
            privateKeyFile: 'eckey.pem'
        }
    }]
});

var container = CloudKit.getDefaultContainer();
var returnstate;
var database = container.publicCloudDatabase;

container.setUpAuth().then(function (userInfo) {
    // Call the function to create a new record
}).catch(function (error) {
    console.log(error);
    // sleep(10000);
    end();
    // end();
});

eventcount = 0;
eventvalue = 0;
// createNewRecord("New Category", "2023-11-12", "New Deliverable", 0.0, "New Lifecycle", "New Start", "New Stop");

query();

function query() {
    database.performQuery({ recordType: 'effortloggeraccont' }).then(function (response) {
        eventcount++;
        if (response.isServerError == true) {
            console.log("has problems 1");
            console.log("has error");
        } else if (response.isError == true) {
            console.log("has error3");
        } else if (response.hasErrors) {
            console.log("has error4");
        } else {
            var a = 0;
            var outputText = ''; // Variable to store the output text

            while (response.records.length > a) {
                // Append values to the output text
                outputText += `${response.records[a].fields.data.value}\n`;
                a++;
            }

            // Write the output text to the file
            fs.writeFileSync('consoleOutput4.txt', outputText);
            //console.log("New record created");
        }
    });
    return returnstate;
}

// Function to create a new CloudKit record

