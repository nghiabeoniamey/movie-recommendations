import logging
import traceback

from fastapi import Request
from fastapi.responses import JSONResponse

logger = logging.getLogger(__name__)


async def global_exception_handler(request: Request, exc: Exception):
    # Log lỗi chi tiết bao gồm traceback
    logger.error(f"Unhandled exception: {exc}")
    logger.error(traceback.format_exc())

    # Trả về thông báo lỗi chung cho client
    return JSONResponse(
        status_code=500,
        content={"detail": "Internal Server Error"}
    )
