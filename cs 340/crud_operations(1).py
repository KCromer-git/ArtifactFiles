import os
from pymongo import MongoClient
from pymongo.errors import ConnectionFailure, OperationFailure

class CRUDOperations:
    def __init__(self, uri: str, db_name: str, collection_name: str):
        """
        Initializes the conenction to the MongoDB database.
        """
        try:
            self.client = MongoClient(uri)
            self.db = self.client[db_name]
            self.collection = self.db[collection_name]
        except ConnectionFailure as e:
            raise Exception(f"Failed to connect to MongoDB:{e}")
    def create(self, data: dict) -> bool:
        try:
            result = self.collection.insert_one(data)
            return result.inserted_id is not None
        except OperationFailure as e:
            return False
    def read(self, query: dict) -> list:
        try:
            cursor = self.collection.find(query)
            return list(cursor)
        except OperationFailure as e:
            return[]
    def update(self, query: dict, update_data: dict) -> int:
        try:
            result = self.collection.update_many(query, {"$set": update_data})
            return result.modified_count
        except OperationFailure as e:
            return 0
    def delete(self, query: dict) -> int:
        try:
            result = self.collection.delete_many(query)
            return result.deleted_count
        except OperationFailure as e:
            return 0

