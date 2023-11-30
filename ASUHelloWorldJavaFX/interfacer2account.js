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
    readValuesFromFileAndCreateRecord();
}).catch(function (error) {
    console.log(error);
    // sleep(10000);
    end();
    // end();
});

// query();

function readValuesFromFileAndCreateRecord() {
    // Read values from the file
    fs.readFile('consoleOutput3.txt', 'utf8', function (err, data) {
        if (err) {
            console.error("Error reading file:", err);
            return;
        }

        // Split the data into an array of lines
        var lines = data.split('\n');

        // Extract values from the lines
        var datag = lines[0].trim();
        console.log(data);
        // Call the function to create a new record
        createNewRecord(datag);
    });
}

// Function to create a new CloudKit record
function createNewRecord(data) {
    var record = {
        recordType: 'effortloggeraccont',
        fields: {
            data: { value: data },
        }
    };

    database.saveRecords([record]).then(function (response) {
        //console.log("New record created");

        // Clear the content of the file after saving the record
        fs.writeFile('consoleOutput3.txt', '', function (err) {
            if (err) {
                console.error("Error clearing file:", err);
            } else {
                //console.log("File cleared");
            }
        });
    }).catch(function (error) {
        console.log("Error creating new record:", error);
    });
}
