import context
import io
import unittest
from contextlib import redirect_stdout
from intro import functions


class MoreFunctionsTestCase(unittest.TestCase):

    def test_more_functions(self):
        with redirect_stdout(io.StringIO()) as f:
            functions.brief_print()
        console_output = f.getvalue()

        self.assertTrue(console_output.__contains__("En 1453 se inventó la imprenta"))
        self.assertEqual(functions.lucky_number(), 64)


if __name__ == '__main__':
    unittest.main()
