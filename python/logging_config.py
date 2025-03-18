import logging

def setup_logging():
    logging.basicConfig(
        level=logging.DEBUG,  # Mức log: DEBUG, INFO, WARNING, ERROR, CRITICAL
        format="%(asctime)s - %(name)s - %(levelname)s - %(message)s"
    )
    return logging.getLogger(__name__)

logger = setup_logging()