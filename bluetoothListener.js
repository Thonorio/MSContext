const { Pool, Client } = require('pg')
var noble = require('noble');

const uuid = String(require('uuid').v4())
const pool = new Pool({
    user: 'context',
    host: 'localhost',
    database: 'context',
    password: 'context',
    port: 5432,
});

pool.connect();

const queryInsert = `
INSERT INTO sensor (uuid, name, humidity, temperature) 
VALUES ('${uuid}',
    'Sensor-${uuid}',
    '0', '0')`;

pool.query(queryInsert, (err, res) => {
    if (err) {
        console.error(err);
        return;
    }
    console.log('Registration Completed');
});

noble.on('stateChange', function (state) {
    if (state === 'poweredOn') {
        noble.startScanning();
    } else {
        noble.stopScanning();
    }
});

noble.on('discover', function (peripheral) {
    noble.stopScanning();
    peripheral.connect(function (error) {
        console.log('connected to peripheral: ' + peripheral.uuid);

        peripheral.discoverServices(['180f'], function (error, services) {
            var batteryService = services[0];
            batteryService.discoverCharacteristics(['2a19'], function (error, characteristics) {
                var batteryLevelCharacteristic = characteristics[0];
                console.log('discovered Battery Level characteristic');

                batteryLevelCharacteristic.on('data', function (data, isNotification) {
                    console.log(uuid)
                    const queryUpdate = `
                    UPDATE sensor
                    set uuid = '${uuid}',
                        name = 'Sensor-${uuid}',
                        humidity = '${data.readUInt8(0)}', temperature = '${data.readUInt8(0)}'
                    WHERE uuid = '${uuid}'`;

                    pool.query(queryUpdate, (err, res) => {
                        if (err) {
                            console.error(err);
                            return;
                        }
                        console.log('Data update successful');
                    });
                });

                // to enable notify
                batteryLevelCharacteristic.subscribe(function (error) {
                    console.log('battery level notification on');
                });
            });
        })
    })
})