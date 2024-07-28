// Sidebar imports
import {
    UilEstate,
    UilClipboardAlt,
    UilUsersAlt,
    UilPackage,
    UilChart,
    UilFileContractDollar
  } from "@iconscout/react-unicons";

  // Sidebar Data
export const SidebarData = [
    {
        href:"/qldv",
      icon: UilEstate,
      heading: "Trang chủ",
    },
    {
        href:"/qldv/khoa",
      icon: UilClipboardAlt,
      heading: "Đoàn cơ sở",
    },
    {
      href:"/qldv/chidoi",
    icon: UilPackage,
    heading: "Chi đội",
  },
    {
        href:"/qldv/doanvien",
      icon: UilUsersAlt,
      heading: "Đoàn viên",
    },
    {
      href:"/qldv/doanphi",
    icon: UilFileContractDollar,
    heading: "Đoàn phí",
  },
    {
        href:"/qldv/thongke",
      icon: UilChart,
      heading: 'Thống kê'
    },
  ];
