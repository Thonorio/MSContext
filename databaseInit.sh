#sh
sudo -u postgres psql -c 'create database context2;'
sudo -u postgres psql -c "create role context PASSWORD 'context';"
sudo -u postgres psql -c 'grant all privileges on database context to context;'
#sudo -u postgres psql -c 'ALTER USER context WITH LOGIN;'
sudo -u postgres psql -c 'ALTER USER context WITH SUPERUSER;'
