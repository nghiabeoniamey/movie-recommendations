import { useState, useEffect, useRef } from "react";

const FileUpload = ({ mode, defaultFiles = [], onFilesChange }) => {
  const [files, setFiles] = useState([]);
  const hiddenInputRef = useRef(null);

  useEffect(() => {
    if (mode === "edit" && defaultFiles.length) {
      const initialFiles = defaultFiles.map((url) => ({
        name: url.split("/").pop() || "",
        size: 0,
        type: "image/*",
        objectURL: url,
        isNew: false,
      }));
      setFiles(initialFiles);
    }
  }, [mode, defaultFiles]);

  const addFile = (file) => {
    const objectURL = URL.createObjectURL(file);
    const newFile = {
      name: file.name,
      size: file.size,
      type: file.type,
      objectURL,
      isNew: true,
    };
    setFiles((prevFiles) => [...prevFiles, newFile]);
    onFilesChange([
      // ...files.map((f) => new File([], f.name, { type: f.type })),
      // file,
      ...files
        .filter((f) => !f.isNew) // Lấy ảnh cũ chưa bị xóa
        .map((f) => new File([], f.name, { type: f.type })),
      file, // Thêm ảnh mới
    ]);
  };

  const handleDelete = (key) => {
    setFiles((prevFiles) => prevFiles.filter((file) => file.objectURL !== key));
    onFilesChange(
      files
        .filter((file) => file.objectURL !== key && !file.isNew)
        .map((file) => new File([], file.name, { type: file.type }))
    );
  };

  return (
    <div className="file-upload">
      {mode === "create" && (
        <div className="flex flex-col items-center justify-center border-2 border-dashed border-gray-300 rounded-lg p-6 cursor-pointer hover:border-gray-500 transition">
          <input
            type="file"
            ref={hiddenInputRef}
            multiple
            className="hidden"
            onChange={(e) =>
              e.target.files && Array.from(e.target.files).forEach(addFile)
            }
          />
          <button
            onClick={() => hiddenInputRef.current?.click()}
            className="flex flex-col items-center gap-2 text-gray-600 hover:text-gray-800 transition"
          >
            <svg
              xmlns="http://www.w3.org/2000/svg"
              fill="none"
              viewBox="0 0 24 24"
              strokeWidth={1.5}
              stroke="currentColor"
              className="w-10 h-10 text-gray-500"
            >
              <path
                strokeLinecap="round"
                strokeLinejoin="round"
                d="M12 4.5v15m7.5-7.5h-15"
              />
            </svg>
            <span className="text-sm font-medium">Upload Files</span>
          </button>

          {files.length > 0 && (
            <div className="grid grid-cols-3 gap-4 mt-4 w-full">
              {files.map((file) => (
                <div key={file.objectURL} className="relative group">
                  <img
                    src={file.objectURL}
                    alt={file.name}
                    className="w-full h-28 object-cover rounded-lg shadow-md transition-transform duration-200 group-hover:scale-105"
                  />
                  <button
                    onClick={() => handleDelete(file.objectURL)}
                    className="absolute top-1 right-1 bg-red-500 text-white rounded-full w-6 h-6 flex items-center justify-center text-sm opacity-80 hover:opacity-100 transition"
                  >
                    ✕
                  </button>
                </div>
              ))}
            </div>
          )}
        </div>
      )}
      {mode === "edit" && (
        <div className="grid grid-cols-3 gap-3 p-2">
          {files.map((file) => (
            <div key={file.objectURL} className="relative group">
              <img
                src={file.objectURL}
                alt={file.name}
                className="w-full h-28 object-cover rounded-lg shadow-md transition-transform duration-200 group-hover:scale-105"
              />
              {/* {mode === "edit" && ( */}
              <button
                onClick={() => handleDelete(file.objectURL)}
                className="absolute top-1 right-1 bg-red-600 text-white rounded-full w-6 h-6 flex items-center justify-center text-sm opacity-80 hover:opacity-100 transition"
              >
                ✕
              </button>
              {/* )} */}
            </div>
          ))}

          <div className="flex flex-col items-center justify-center border-2 border-dashed border-gray-400 rounded-lg h-28 cursor-pointer hover:border-gray-600 transition">
            <input
              type="file"
              ref={hiddenInputRef}
              multiple
              className="hidden"
              onChange={(e) =>
                e.target.files && Array.from(e.target.files).forEach(addFile)
              }
            />
            <button
              onClick={() => hiddenInputRef.current?.click()}
              className="text-gray-600 hover:text-gray-800 transition"
            >
              + Upload
            </button>
          </div>
        </div>
      )}
    </div>
  );
};

export default FileUpload;
