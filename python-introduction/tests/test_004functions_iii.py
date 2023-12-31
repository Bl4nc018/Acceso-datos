import context
import io
import unittest
from contextlib import redirect_stdout
from intro import functions


class BigPrintTestCase(unittest.TestCase):

    def test_long_print(self):
        with redirect_stdout(io.StringIO()) as f:
            functions.long_print()
        console_output = f.getvalue()

        self.assertTrue(console_output.__contains__("programador"))


if __name__ == '__main__':
    unittest.main()
