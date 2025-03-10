import { useState } from "react";
// import DatePicker from "react-datepicker";
// import "react-datepicker/dist/react-datepicker.css";

const Filter = () => {
  return (
    <div className="m-2 max-w-screen-full bg-gray-900 text-white">
      <div className="rounded-xl border border-gray-700 bg-gray-800 p-4 shadow-lg">
        <div className="mt-8 grid grid-cols-1 gap-6 md:grid-cols-2 lg:grid-cols-3 xl:grid-cols-4">
          <div className="flex flex-col">
            <label htmlFor="name" className="text-gray-300 text-sm font-medium">
              Name
            </label>
            <input
              type="text"
              id="name"
              placeholder="raspberry juice"
              className="mt-2 block w-full rounded-md border border-gray-600 bg-gray-700 px-2 py-2 text-white shadow-sm outline-none focus:border-blue-400 focus:ring focus:ring-blue-300 focus:ring-opacity-50"
            />
          </div>

          <div className="flex flex-col">
            <label
              htmlFor="manufacturer"
              className="text-gray-300 text-sm font-medium"
            >
              Manufacturer
            </label>
            <input
              type="text"
              id="manufacturer"
              placeholder="cadbery"
              className="mt-2 block w-full rounded-md border border-gray-600 bg-gray-700 px-2 py-2 text-white shadow-sm outline-none focus:border-blue-400 focus:ring focus:ring-blue-300 focus:ring-opacity-50"
            />
          </div>

          <div className="flex flex-col">
            <label htmlFor="year" className="text-gray-300 text-sm font-medium">
              Year of Entry
            </label>
            <select
              id="year"
              className="mt-2 block w-full rounded-md border border-gray-600 bg-gray-700 px-2 py-2 text-white shadow-sm outline-none focus:border-blue-400 focus:ring focus:ring-blue-300 focus:ring-opacity-50"
            >
              {Array.from({ length: 10 }, (_, i) => {
                const year = 2000 + i;
                return (
                  <option key={year} value={year} className="text-black">
                    {year}
                  </option>
                );
              })}
            </select>
          </div>

          <div className="flex flex-col">
            <label
              htmlFor="status"
              className="text-gray-300 text-sm font-medium"
            >
              Status
            </label>
            <select
              id="status"
              className="mt-2 block w-full rounded-md border border-gray-600 bg-gray-700 px-2 py-2 text-white shadow-sm outline-none focus:border-blue-400 focus:ring focus:ring-blue-300 focus:ring-opacity-50"
            >
              <option className="text-black">Dispached Out</option>
              <option className="text-black">In Warehouse</option>
              <option className="text-black">Being Brought In</option>
            </select>
          </div>
        </div>

        <div className="mt-6 flex justify-end space-x-4">
          <button className="rounded-lg bg-gray-700 px-8 py-2 font-medium text-gray-300 outline-none focus:ring focus:ring-gray-500 hover:bg-gray-600 active:scale-95">
            Reset
          </button>
          <button className="rounded-lg bg-blue-500 px-8 py-2 font-medium text-white outline-none focus:ring focus:ring-blue-400 hover:bg-blue-600 active:scale-95">
            Search
          </button>
        </div>
      </div>
    </div>
  );
};

export default Filter;
