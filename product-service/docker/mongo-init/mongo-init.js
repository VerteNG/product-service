print("Creating mongo_dev application user...");
db = db.getSiblingDB("mongo_dev");
db.createUser({
    user: "mongo_dev",
    pwd: "mongo_dev",
    roles: [{role: "readWrite", db: "mongo_dev"}],
});
print("mongo_dev user created");
