import bcrypt
import context
import os
import requests
import signal
import subprocess
import threading
import time
import unittest


class IdeAPILoginNotHappyPathTestCase(unittest.TestCase):

    def test_missing_param_password(self):
        def test_body():
            request_body_missing_param = {
                "login_email": "test_" + str(time.time()) + "@test.co.uk",
            }
            self.__assert_post_endpoint("v1/sessions", request_body_missing_param, {"error": "Missing parameter"}, 400)

        # Run server and run test
        self.__test_with_server_alive(test_body)
    
    def test_missing_param_email(self):
        def test_body():
            request_body_missing_param = {
                "password": "asdf12345"
            }
            self.__assert_post_endpoint("v1/sessions", request_body_missing_param, {"error": "Missing parameter"}, 400)

        # Run server and run test
        self.__test_with_server_alive(test_body)

    def test_user_not_found(self):
        def test_body():
            request_body = {
                "login_email": "test_" + str(time.time()) + "@test.co.uk", # Never registered before
                "password": "asdf12345"
            }
            self.__assert_post_endpoint("v1/sessions", request_body, {"error": "User not in our system"}, 404)

        # Run server and run test
        self.__test_with_server_alive(test_body)
    
    def test_invalid_password(self):
        # INSERT an user directly into the DB
        valid_email = "test_" + str(time.time()) + "@test.co.uk"
        valid_pass = "asdfasdf"
        hashed = bcrypt.hashpw(valid_pass.encode('utf8'), bcrypt.gensalt()).decode('utf8')
        self.assertTrue(os.path.isdir(context.IDEAPI_ROOT + "idearest04app"))
        command = 'sqlite3.exe apis/IdeAPI/db.sqlite3 "INSERT INTO idearest04app_customuser (email, username, encrypted_password) VALUES (\'' + valid_email + '\', \'Test User\', \'' + hashed + '\')"'
        output = subprocess.Popen(command, shell=True, cwd=context.PROJECT_ROOT, stdout=subprocess.PIPE).communicate()[0]

        # Now assert 401
        def send_request():
            request_body = {
                "login_email": valid_email,
                "password": "qwertyqwerty"
            }
            self.__assert_post_endpoint("v1/sessions", request_body, {"error": "Invalid password"}, 401)

        # Run server and run test
        self.__test_with_server_alive(send_request)


    def __test_with_server_alive(self, block):
        def run_server():
            proc = subprocess.Popen("python manage.py runserver", creationflags=subprocess.CREATE_NEW_PROCESS_GROUP, cwd=context.IDEAPI_ROOT)
            time.sleep(4)
            proc.send_signal(signal.CTRL_BREAK_EVENT)
            time.sleep(1)
            proc.kill()

        def app_dir_exists():
            return os.path.isdir(context.IDEAPI_ROOT + "idearest04app")

        self.assertTrue(app_dir_exists())
        t = threading.Thread(target=run_server)
        t.start()
        time.sleep(1)
        block()
        t.join(timeout=6)
        
    def __assert_post_endpoint(self, endpoint, json_body, json_response, status_code=200):
        response = requests.post("http://localhost:8000/" + endpoint, json=json_body)
        self.assertEqual(response.status_code, status_code)
        self.assertEqual(response.json(), json_response)
      

if __name__ == '__main__':
    unittest.main()
