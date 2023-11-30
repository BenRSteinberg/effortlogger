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
    fs.readFile('consoleOutput.txt', 'utf8', function (err, data) {
        if (err) {
            console.error("Error reading file:", err);
            return;
        }

        // Split the data into an array of lines
        var lines = data.split('\n');

        // Extract values from the lines
        var category = lines[0].trim();
        var date = lines[1].trim();
        var deliverable = lines[2].trim();
        var dtime = lines[3].trim();
        var lifecycle = lines[4].trim();
        var start = lines[5].trim();
        var stop = lines[6].trim();
        var userv = lines[7].trim();

        // Call the function to create a new record
        createNewRecord(category, date, deliverable, dtime, lifecycle, start, stop, userv);
    });
}

// Function to create a new CloudKit record
function createNewRecord(category, date, deliverable, dtime, lifecycle, start, stop, userv) {
    var record = {
        recordType: 'effortloggerdata',
        fields: {
            Category: { value: category },
            date: { value: date },
            deliverable: { value: deliverable },
            dtime: { value: dtime },
            lifecycle: { value: lifecycle },
            start: { value: start },
            stop: { value: stop },
            user: { value: userv }
        }
    };

    database.saveRecords([record]).then(function (response) {
        //console.log("New record created");

        // Clear the content of the file after saving the record
        fs.writeFile('consoleOutput.txt', '', function (err) {
            if (err) {
                console.error("Error clearing file:", err);
            } else {
                //console.log("File cleared");
            }
        });
    }).catch(function (error) {
       // console.log("Error creating new record:", error);
    });
}
